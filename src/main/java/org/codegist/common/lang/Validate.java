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

package org.codegist.common.lang;

public final class Validate {
    private Validate(){
        throw new IllegalStateException();
    }

    public static void isFalse(boolean condition, String errorMessage){
        isTrue(!condition, errorMessage);
    }
    public static void isTrue(boolean condition, String errorMessage){
        if(!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static void notNull(Object arg, String errorMessage){
        if(arg == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void notBlank(String arg, String errorMessage){
        if(Strings.isBlank(arg)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
