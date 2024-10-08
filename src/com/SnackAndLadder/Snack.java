package com.SnackAndLadder;

class Snake extends SpecialEntity {
    public Snake(int start, int end) {
        super(start, end);
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid snake positions");
        }
    }

    @Override
    public boolean isValid() {
        return start > end;
    }
}