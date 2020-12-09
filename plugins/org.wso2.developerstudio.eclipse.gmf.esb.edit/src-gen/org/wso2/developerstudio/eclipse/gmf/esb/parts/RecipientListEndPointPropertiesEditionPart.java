/**
 * Generated with Acceleo
 */
package org.wso2.developerstudio.eclipse.gmf.esb.parts;

// Start of user code for imports
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;


// End of user code

/**
 * 
 * 
 */
public interface RecipientListEndPointPropertiesEditionPart {

	/**
	 * @return the description
	 * 
	 */
	public String getDescription();

	/**
	 * Defines a new description
	 * @param newValue the new description to set
	 * 
	 */
	public void setDescription(String newValue);


	/**
	 * @return the commentsList
	 * 
	 */
	public EList getCommentsList();

	/**
	 * Defines a new commentsList
	 * @param newValue the new commentsList to set
	 * 
	 */
	public void setCommentsList(EList newValue);

	/**
	 * Add a value to the commentsList multivalued attribute.
	 * @param newValue the value to add
	 */
	public void addToCommentsList(Object newValue);

	/**
	 * Remove a value to the commentsList multivalued attribute.
	 * @param newValue the value to remove
	 */
	public void removeToCommentsList(Object newValue);


	/**
	 * @return the endPointName
	 * 
	 */
	public String getEndPointName();

	/**
	 * Defines a new endPointName
	 * @param newValue the new endPointName to set
	 * 
	 */
	public void setEndPointName(String newValue);


	/**
	 * @return the anonymous
	 * 
	 */
	public Boolean getAnonymous();

	/**
	 * Defines a new anonymous
	 * @param newValue the new anonymous to set
	 * 
	 */
	public void setAnonymous(Boolean newValue);


	/**
	 * @return the InLine
	 * 
	 */
	public Boolean getInLine();

	/**
	 * Defines a new InLine
	 * @param newValue the new InLine to set
	 * 
	 */
	public void setInLine(Boolean newValue);


	/**
	 * @return the duplicate
	 * 
	 */
	public Boolean getDuplicate();

	/**
	 * Defines a new duplicate
	 * @param newValue the new duplicate to set
	 * 
	 */
	public void setDuplicate(Boolean newValue);




	/**
	 * Init the properties
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initProperties(ReferencesTableSettings settings);

	/**
	 * Update the properties
	 * @param newValue the properties to update
	 * 
	 */
	public void updateProperties();

	/**
	 * Adds the given filter to the properties edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToProperties(ViewerFilter filter);

	/**
	 * Adds the given filter to the properties edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToProperties(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the properties table
	 * 
	 */
	public boolean isContainedInPropertiesTable(EObject element);


	/**
	 * @return the reversed
	 * 
	 */
	public Boolean getReversed();

	/**
	 * Defines a new reversed
	 * @param newValue the new reversed to set
	 * 
	 */
	public void setReversed(Boolean newValue);




	/**
	 * Init the Children
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initChildren(ReferencesTableSettings settings);

	/**
	 * Update the Children
	 * @param newValue the Children to update
	 * 
	 */
	public void updateChildren();

	/**
	 * Adds the given filter to the Children edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToChildren(ViewerFilter filter);

	/**
	 * Adds the given filter to the Children edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToChildren(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the Children table
	 * 
	 */
	public boolean isContainedInChildrenTable(EObject element);


	/**
	 * @return the name
	 * 
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * 
	 */
	public void setName(String newValue);




	/**
	 * Init the outputConnector
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initOutputConnector(ReferencesTableSettings settings);

	/**
	 * Update the outputConnector
	 * @param newValue the outputConnector to update
	 * 
	 */
	public void updateOutputConnector();

	/**
	 * Adds the given filter to the outputConnector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOutputConnector(ViewerFilter filter);

	/**
	 * Adds the given filter to the outputConnector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOutputConnector(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the outputConnector table
	 * 
	 */
	public boolean isContainedInOutputConnectorTable(EObject element);


	/**
	 * @return the endpointType
	 * 
	 */
	public Enumerator getEndpointType();

	/**
	 * Init the endpointType
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initEndpointType(Object input, Enumerator current);

	/**
	 * Defines a new endpointType
	 * @param newValue the new endpointType to set
	 * 
	 */
	public void setEndpointType(Enumerator newValue);


	/**
	 * @return the endpointsValue
	 * 
	 */
	public String getEndpointsValue();

	/**
	 * Defines a new endpointsValue
	 * @param newValue the new endpointsValue to set
	 * 
	 */
	public void setEndpointsValue(String newValue);


	/**
	 * @return the maxCache
	 * 
	 */
	public String getMaxCache();

	/**
	 * Defines a new maxCache
	 * @param newValue the new maxCache to set
	 * 
	 */
	public void setMaxCache(String newValue);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods
	
	// End of user code

}
