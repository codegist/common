/*
 * Copyright 2010 CodeGist.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.common.collect;

import java.util.Collection;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Collections {

    private Collections() {
        throw new IllegalStateException();
    }

    /**
     * Looks through all the collections and checks if all are null or empty
     *
     * @param collections Collections to check
     * @return return true if all the collections are empty or null
     */
    public static boolean areEmpties(Collection... collections) {
        for (Collection m : collections) {
            if (m != null && !m.isEmpty()) return false;
        }
        return true;
    }
}
