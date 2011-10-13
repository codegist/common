/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.lang;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Disposables {

    private Disposables(){
        throw new IllegalStateException();
    }

    public static void dispose(Object o){
        if(o instanceof Disposable) {
            try {
                ((Disposable) o).dispose();
            }catch(Exception e){
                // ignore
            }
        }
    }

    public static void dispose(Object... os){
        for(Object o : os){
            dispose(o);
        }
    }

}
