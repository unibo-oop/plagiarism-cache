package casim.utils;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;

/**
 * A result that allow to encapsulate return values of functions that may throw an exception.
 *
 * The original idea for the implementation and the use of this class was taken here:
 *   https://github.com/Zeegomo/nwoolcan/blob/master/src/main/java/nwoolcan/utils/Result.java
 *
 * @param <T> Result type.
 */
public final class Result<T> {
    private static final Empty EMPTY_VALUE = new Empty() { }; 

    private final Optional<T> value;
    private final Optional<Exception> exception; 

    private Result(final Optional<T> value, final Optional<Exception> exception) {
        this.value = value;
        this.exception = exception;
    }

    /**
     * Execute a supplier of T and return a {@link Result} holding the value.
     * The execution may throw an exception.
     *
     * @param <T> the return type of the supplier.
     * @param <E> the exception type.
     * @param function the supplier to be executed.
     * @return a {@link Result} holding the value.
     */
    public static <T, E extends Exception> Result<T> executeSupplier(final FailableSupplier<T, E> function) {
        try {
            return Result.of(function.run());
        } catch (Exception ex) { //NOPMD:should catch every exceptions
            return Result.error(ex);
        }
    }

    /**
     * Execute a task that may thow an exception and return a {@link Result} holding the value.
     *
     * @param <E> the exception that the task may throw.
     * @param function the supplier to be executed.
     * @return a {@link Result} holding {@link Empty} if the task is completed successfully.
     */
    public static <E extends Exception> Result<Empty> executeTask(final Task<E> function) {
        try {
            function.execute();
            return Result.ofEmpty();
        } catch (Exception ex) { //NOPMD:should catch every exceptions
            return Result.error(ex);
        }
    }

    /** 
     * Create a {@link Result} that contains value.
     * 
     * @param <T> The type of value.
     * @param value the value to be encapsulated.
     * @return {@link Result} containing value.
     */
    public static <T> Result<T> of(final T value) {
        return new Result<>(Optional.of(value), Optional.empty());
    }

    /** 
     * Create a {@link Result} that contains exception.
     * 
     * @param <T> The type of the value to be returned.
     * @param exception the exception to be ecapsulated.
     * @return {@link Result} containing exception.
     */
    public static <T> Result<T> error(final Exception exception) {
        return new Result<>(Optional.empty(), Optional.of(exception));
    }

    /** 
     * Return a {@link Result} containing {@link Empty}.
     * 
     * @return An empty {@link Result}.
     */
    public static Result<Empty> ofEmpty() {
        return Result.of(EMPTY_VALUE);
    }

    /** 
     * Return true if {@link Result} contains a value.
     * 
     * @return whether the {@link Result} contains a value.
     */
    public boolean isPresent() {
        return this.value.isPresent();
    }

    /** 
     * Return true if {@link Result} contains an exception.
     * 
     * @return whether the {@link Result} contains an exception.
     */
    public boolean isError() {
        return this.exception.isPresent();
    }

    /** 
     * Return the value contained in {@link Result} if any, otherwise throws a NoSuchElementException.
     * 
     * @return the value contained in {@link Result}.
     */
    public T getValue() {
        return this.value.get();
    }

    /** 
     * Return the exception contained in {@link Result} if any, otherwise throws a NoSuchElementException.
     * 
     * @return the exception contained in {@link Result}
     */
    public Exception getError() {
        return this.exception.get();
    }

    /**
     * If the value is present call consumer with value as parameter, otherwise do nothing.
     * 
     * @param consumer function to call with value as parameter.
     */
    public void ifPresent(final Consumer<? super T> consumer) {
        this.value.ifPresent(consumer);
    }

    /** 
     * If a value is present, apply the provided function to it returning a {@link Result} describing it. Otherwise return a {@link Result} containing the original exception.
     *
     * @param <U> the type of the result of the mapping function.
     * @param mapper a mapping {@link Function} that has to be applied to the value, if present.
     * @return a {@link Result} describing the result of mapper if the this contains a value, the original {@link Result} containing the exception otherwise.
     */
    @SuppressWarnings("unchecked")
    public <U> Result<U> map(final Function<? super T, ? extends U> mapper) {
        return this.isPresent()
            ? Result.of(mapper.apply(this.getValue()))
            : (Result<U>) this;
    }

    /** 
     * If a value is present apply the provided {@link Result}-bearing function to it returning that {@link Result}. Otherwise return a {@link Result} holding the original exception.
     * 
     * @param <U> the type of the value contained in the {@link Result} returned by the mapper function.
     * @param mapper the {@link Function} to apply to the {@link Result} value, similar to {@link #map(Function) map} but the mapper function already returns a {@link Result}, flatMap does not wrap with an additional {@link Result}.
     * @return a {@link Result} returned by the mapper function if a value is present, a {@link Result} with the original exception otherwise.
     */
    @SuppressWarnings("unchecked")
    public <U> Result<U> flatMap(final Function<? super T, Result<U>> mapper) {
        return this.isPresent()
            ? mapper.apply(this.getValue())
            : (Result<U>) this;
    }

    /** 
     * If a value is present apply the provided {@link Result}-bearing function to it returning that {@link Result}. Otherwise return a {@link Result} holding the original exception.
     * 
     * @param <U> the type of the value contained in the {@link Result} generated by the {@link Supplier} function.
     * @param supplier a {@link Supplier} of {@link Result}
     * @return a {@link Result} returned by the supplier or the original exception if the value is not present.
     */
    @SuppressWarnings("unchecked")
    public <U> Result<U> flatMap(final Supplier<Result<U>> supplier) {
        return this.isPresent() ? supplier.get() : (Result<U>) this;
    }

    /** 
     * Return the value of the {@link Result} if present, the other value passed otherwise.
     * 
     * @param other the value to return if {@link Result} contains an exception.
     * @return the {@link Result}'s value if present, other otherwise.
     */
    public T orElse(final T other) {
        return this.isPresent() ? this.getValue() : other;
    }

    /** 
     * If the value is not present, or if the value satisfies the given predicate return this. Otherwise return a {@link Result} holding an {@link IllegalArgumentException}.
     *
     * @param predicate the predicate to apply to the value of the {@link Result} if present.
     * @return a {@link Result} describing the value of this if a value is present and matches the predicate, a {@link Result} containing {@link IllegalArgumentException} otherwise.
     */
    public Result<T> require(final Predicate<? super T> predicate) {
        return this.require(predicate, new IllegalArgumentException());
    }

    /** 
     * If the value is not present, or if the value satisfies the given predicate return this. Otherwise return a {@link Result} holding exception.
     * 
     * @param predicate the predicate to apply to the value of the {@link Result} if present.
     * @param exception the exception to return int the {@link Result} if the predicate does not match.
     * @return a {@link Result} containing the value of this if present and if it matches the predicate, a {@link Result} holding exception otherwise.
     */
    public Result<T> require(final Predicate<? super T> predicate, final Exception exception) {
        return this.require(predicate, () -> exception);
    }

    /** 
     * If the value is not present or if it matches the predicate, return this. Otherwise return a {@link Result} containing exception.
     * 
     * @param predicate the predicate to apply to the value of the {@link Result} if present.
     * @param exception a supplier of {@link Exception} to use if the predicate does not match.
     * @return a {@link Result} containing the value of this if present and if it matches the predicate, a {@link Result} holding exception otherwise.
     */
    public Result<T> require(final Predicate<? super T> predicate, final Supplier<Exception> exception) {
        if (this.isPresent()) {
            return predicate.test(this.getValue()) ? this : Result.error(exception.get());
        } else {
            return this;
        }
    }

    /** 
     * Apply mapper to the exception, if exists.
     * 
     * @param mapper the function to apply to the exception.
     * @return a {@link Result} containing the mapped exception if present, this otherwise.
     */
    public Result<T> mapError(final Function<Exception, ? extends Exception> mapper) {
        return this.isError()
            ? Result.error(mapper.apply(this.getError()))
            : this;
    }

    /** 
     * Return an {@link Optional} contaning value if present, empty otherwise.
     * 
     * @return an {@link Optional} if the value is present, empty otherwise.
     */
    public Optional<T> toOptional() {
        return this.isPresent()
            ? Optional.of(this.getValue())
            : Optional.empty();
    }

    /** 
     * Map this {@link Result} to a {@link Result} of {@link Empty}.
     * 
     * @return a {@link Result} of {@link Empty}
     */
    public Result<Empty> toEmpty() {
        return this.flatMap(Result::ofEmpty);
    }

    /** 
     * Return a {@link Stream} of this {@link Result}.
     * 
     * @return a {@link Stream} of this.
     */
    public Stream<Result<T>> stream() {
        return Stream.of(this);
    }

    /** 
     * Return a simple string representation of the value/exception that {@link Result} contains.
     * 
     * @return string representation of {@link Result} content.
     */
    @Override
    public String toString() {
        return this.isPresent() ? this.getValue().toString() : this.getError().toString();
    }

    /** 
     * Return true if some other object "obj" is equal to this {@link Result}.
     * "obj" is considered equal if:
     *  - it is a {@link Result}, and:
     *  - the value contained in "obj" compares equal to the value contained in "this" via equals().
     * 
     * @param obj the object to be compared to the calling {@link Result}.
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Result)) {
            return false;
        }

        final Result<?> other = (Result<?>) obj;

        return this.isPresent() 
            ? this.value.equals(other.value)
            : this.exception.equals(other.exception);
    }

    /** 
     * Return the hashcode of the present value, 0 if there is no value.
     * 
     * @return hash code of the contained value, 0 if there is no value.
     */
    @Override
    public int hashCode() {
        return this.isPresent() ? this.getValue().hashCode() : 0;
    }
}
