/*
 * Copyright 2010 CodeGist.org
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

package org.codegist.common.reflect;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laurent.gilles@codegist.org
 */
public final class Annotations {
    private Annotations(){
        throw new IllegalStateException();
    }

    public static Map<Class<? extends Annotation>, Annotation> toMap(Annotation[] annotations) {
        Map<Class<? extends Annotation>, Annotation> map = new HashMap<Class<? extends Annotation>, Annotation>();
        for(Annotation a : annotations){
            map.put(a.getClass(), a);
        }
        return map;
    }
}
