package com.restaurant.exercice.enumeration;

public enum UnPaidOrderState {
    NOTHING_TO_DECLARE,
    PINNED,
    TO_TRANSFER_TO_AUTHORITIES,
    TRANSFERRED_TO_AUTHORITIES;

    public boolean isPinned() {
        return this.equals(PINNED);
    }

    public boolean isToTransferToAuthorities() {
        return this.equals(TO_TRANSFER_TO_AUTHORITIES);
    }

    public boolean isTransferredToAuthorities() {
        return this.equals(TRANSFERRED_TO_AUTHORITIES);
    }
}
