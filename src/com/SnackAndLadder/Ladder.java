package com.SnackAndLadder;

class Ladder extends SpecialEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid ladder positions");
        }
    }

    @Override
    public boolean isValid() {
        return start < end;
    }
}
