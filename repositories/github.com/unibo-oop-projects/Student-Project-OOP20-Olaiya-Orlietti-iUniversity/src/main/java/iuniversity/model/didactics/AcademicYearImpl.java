package iuniversity.model.didactics;

import java.time.LocalDate;

public class AcademicYearImpl implements AcademicYear {

    private LocalDate start;
    private LocalDate end;
    private LocalDate firstTermStart;
    private LocalDate firstTermEnd;
    private LocalDate secondTermStart;
    private LocalDate secondTermEnd;
    
    public AcademicYearImpl(LocalDate start, LocalDate end, LocalDate firstTermStart, LocalDate firstTermEnd,
            LocalDate secondTermStart, LocalDate secondTermEnd) {
        super();
        this.start = start;
        this.end = end;
        this.firstTermStart = firstTermStart;
        this.firstTermEnd = firstTermEnd;
        this.secondTermStart = secondTermStart;
        this.secondTermEnd = secondTermEnd;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public LocalDate getFirstTermStart() {
        return this.firstTermStart;
    }

    @Override
    public LocalDate getFirstTermEnd() {
        return this.firstTermEnd;
    }

    @Override
    public LocalDate getSecondTermStart() {
        return this.secondTermStart;
    }

    @Override
    public LocalDate getSecondTermEnd() {
        return this.secondTermEnd;
    }
    
}
