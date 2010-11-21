package org.codegist.common.collect;

import java.util.Collection;

public final class Collections {
    private Collections() {
    }

    /**
     * Looks through all the collections and checks if all are null or empty
     *
     * @param collections Collections to check
     * @return return true if all the collections are empty or null
     */
    public static boolean areEmpties(Collection... collections) {
        if (collections == null) return true;
        for (Collection m : collections) {
            if (m != null && !m.isEmpty()) return false;
        }
        return true;
    }
}
