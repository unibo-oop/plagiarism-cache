package main.java.model.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.model.Shape;

public class AutofillableQueue<T> {
	
	private Queue<T> queue;
	private Supplier<T> supplier;

	public AutofillableQueue(final Supplier<T> supplier, final int size) {
		this.supplier = supplier;	
		this.queue = new LinkedList<>(Stream.of(this.supplier.get())
			.limit(size)
			.collect(Collectors.toList()));
		/*for version:
		
		this.queue = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			this.queue.add(this.supplier.get());
		}*/
	}
	
	public List<T> getQueue() {
		return new ArrayList<>(queue);
	}
	
	public T getNext() {
		this.queue.add(this.supplier.get());
		return this.queue.poll();
	}
	
	public static void main() {
		@SuppressWarnings("unused")
		AutofillableQueue<Shape> q = new AutofillableQueue<>(new Supplier<Shape>() {

			private List<Shape> shapes = Arrays.asList(Shape.values());
			private long seed = 543532;
			private Iterator<Shape> random = new Random(seed)
					.ints(0, Shape.values().length)
					.mapToObj(shapes::get)
					.iterator();
			
			@Override
			public Shape get() {
				return random.next();
			}
		}, 3);
	}
}
