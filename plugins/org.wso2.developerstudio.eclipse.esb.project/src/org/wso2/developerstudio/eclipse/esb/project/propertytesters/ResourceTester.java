/*
 * Copyright 2016 WSO2, Inc. (http://wso2.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.developerstudio.eclipse.esb.project.propertytesters;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;

public class ResourceTester extends PropertyTester{

	@Override
	public boolean test(Object receiver, String property, Object[] args,
	        Object expectedValue) {
		
		    //If the selected item is an resource, then disable the item
	    	if(receiver instanceof IResource){
	    		return false;
	    	}else{
	    		return true;
	    	}
	}
}
