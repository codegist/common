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

public final class State {
    private State(){
        throw new IllegalStateException();
    }


    public static void isTrue(boolean condition, String errorMessage, Object... params){
        if(!condition) {
            throw new IllegalStateException(String.format(errorMessage, params));
        }
    }
    public static void notNull(Object arg, String errorMessage, Object... params){
        if(arg == null) {
            throw new IllegalStateException(String.format(errorMessage, params));
        }
    }

    public static void notBlank(String arg, String errorMessage, Object... params){
        if(Strings.isBlank(arg)) {
            throw new IllegalStateException(String.format(errorMessage, params));
        }
    }

}
