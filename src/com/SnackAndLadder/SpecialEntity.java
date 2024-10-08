package com.SnackAndLadder;

abstract class SpecialEntity {
    protected int start;
    protected int end;

    public SpecialEntity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public abstract boolean isValid();
}
