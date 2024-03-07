package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@code remark}.
     *
     * @param remark A valid address.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.value = remark;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // "other" is not a remark
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return this.value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
