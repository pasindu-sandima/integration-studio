/*
 * Copyright (c) 2011-2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.integrationstudio.artifact.mediator.ui.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.wso2.integrationstudio.artifact.mediator.Activator;
import org.wso2.integrationstudio.artifact.mediator.model.CustomMediatorModel;
import org.wso2.integrationstudio.artifact.mediator.template.CustomMediatorClassTemplate;
import org.wso2.integrationstudio.artifact.mediator.utils.MediatorImageUtils;
import org.wso2.integrationstudio.esb.libraries.utils.LibraryUtils;
import org.wso2.integrationstudio.logging.core.IIntegrationStudioLog;
import org.wso2.integrationstudio.logging.core.Logger;
import org.wso2.integrationstudio.maven.util.MavenUtils;
import org.wso2.integrationstudio.platform.core.model.MavenInfo;
import org.wso2.integrationstudio.platform.core.project.presentation.ProjectPresentation;
import org.wso2.integrationstudio.platform.ui.wizard.AbstractWSO2ProjectCreationWizard;
import org.wso2.integrationstudio.platform.ui.wizard.pages.ProjectOptionsDataPage;
import org.wso2.integrationstudio.utils.jdt.JavaUtils;
import org.wso2.integrationstudio.utils.project.ProjectUtils;

public class CustomMediatorCreationWizard extends AbstractWSO2ProjectCreationWizard {
	
	private static final String PROJECT_WIZARD_WINDOW_TITLE = "New Mediator Artifact";
    private static final String MEDIATOR_PROJECT_NATURE = "org.wso2.developerstudio.eclipse.artifact.mediator.project.nature";
    private static final String MEDIATOR_PROJECT_MAVEN_CONFIGURED_NATURE = "org.eclipse.m2e.core.maven2Nature";
	private static final String ESB_GRAPHICAL_PERSPECTIVE = "org.wso2.integrationstudio.gmf.esb.diagram.custom.perspective";
    
	private  CustomMediatorModel customMediatorModel;
    private  IProject project;
    private static IIntegrationStudioLog log=Logger.getLog(Activator.PLUGIN_ID);
	
    public CustomMediatorCreationWizard(){
     setCustomMediatorModel(new CustomMediatorModel());
     setModel(customMediatorModel);
     setWindowTitle(PROJECT_WIZARD_WINDOW_TITLE);
     setDefaultPageImageDescriptor(MediatorImageUtils.getInstance().getImageDescriptor("new-mediator-wizard.png"));
    }
    
    public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if(page instanceof ProjectOptionsDataPage ){
				if(getModel().getSelectedOption().equalsIgnoreCase("import.mediator")){
					IProject temp =customMediatorModel.getMediatorProject();
					File pomfile = temp.getFile("pom.xml").getLocation().toFile();
					if(!pomfile.exists()){
				      try {
				    	  getModel().setMavenInfo(new MavenInfo("org.wso2.carbon." + temp.getName(),temp.getName(),
				    			  "1.0.0"));
					      getModel().setProjectName(temp.getName());
					} catch (Exception e) {
						log.error("project update fail",e);
					}
					}else{					    
					   nextPage=null;
					}
				}
		}
		return nextPage;
	}
   
	public boolean performFinish(){
	    try {
	    	
			if(customMediatorModel.getSelectedOption().equals("new.mediator")){
				setProjectNature(MEDIATOR_PROJECT_NATURE);
				project = createNewProject();
				IFolder  srcFolder= ProjectUtils.getWorkspaceFolder(project, "src", "main","java");
		     	JavaUtils.addJavaSupportAndSourceFolder(project, srcFolder);	     	
		     	
		    	/*create the new Java project*/
		     	String className = customMediatorModel.getMediatorClassName();
		     	String packageName = customMediatorModel.getMediatorClassPackageName();
		     	IJavaProject iJavaProject = JavaCore.create(project);
		     	IPackageFragmentRoot root = iJavaProject.getPackageFragmentRoot(srcFolder);
		     	IPackageFragment sourcePackage = root.createPackageFragment(packageName, false, null);
		     	
		     	/*get the Mediator class template*/
		     	String template = CustomMediatorClassTemplate.getClassTemplete(packageName, className);
				ICompilationUnit cu = sourcePackage.createCompilationUnit(className+".java", template, false, null);

				project.refreshLocal(IResource.DEPTH_INFINITE,new NullProgressMonitor());
				try {
					IEditorPart javaEditor = JavaUI.openInEditor(cu);
					JavaUI.revealInEditor(javaEditor, (IJavaElement) cu);
				} catch (Exception e) {
					log.error(e);
				}
			} else{
		     project = customMediatorModel.getMediatorProject();
			 project.refreshLocal(IResource.DEPTH_INFINITE,new NullProgressMonitor());
			}
			File pomfile = project.getFile("pom.xml").getLocation().toFile();
			getModel().getMavenInfo().setPackageName("bundle");
			if(!pomfile.exists()){
				createPOM(pomfile);
				MavenProject mavenProject = MavenUtils.getMavenProject(pomfile);
				mavenProject.getModel().getProperties().put("CApp.type", "lib/synapse/mediator");
				MavenUtils.saveMavenProject(mavenProject, pomfile);
				addDependancies(project);
			}
			MavenProject mavenProject = MavenUtils.getMavenProject(pomfile);
			boolean pluginExists = MavenUtils.checkOldPluginEntry(mavenProject,
					"org.apache.felix", "maven-bundle-plugin",
					"2.3.4");
			
			// add dependencies
			List<Dependency> dependencies = new ArrayList<>();
            dependencies.add(MavenUtils.createDependency("org.apache.synapse", "synapse-core",
                    "2.1.7-wso2v227"));
            dependencies.add(MavenUtils.createDependency("org.apache.axis2.wso2", "axis2",
                    "1.6.1-wso2v63"));
            dependencies.add(MavenUtils.createDependency("commons-logging", "commons-logging",
                    "1.1.1"));
            MavenUtils.addMavenDependency(mavenProject, dependencies);
            
			if(!pluginExists){
				Plugin plugin = MavenUtils
				.createPluginEntry(mavenProject, "org.apache.felix",
						"maven-bundle-plugin", "2.3.4", true);
		
				Xpp3Dom configurationNode = MavenUtils.createMainConfigurationNode(plugin);
				Xpp3Dom instructionNode = MavenUtils.createXpp3Node("instructions");
				Xpp3Dom bundleSymbolicNameNode = MavenUtils.createXpp3Node(instructionNode, "Bundle-SymbolicName");
				Xpp3Dom bundleNameNode = MavenUtils.createXpp3Node(instructionNode, "Bundle-Name");;
				Xpp3Dom exportPackageNode =
				MavenUtils.createXpp3Node(instructionNode, "Export-Package");
				Xpp3Dom dynamicImportNode = MavenUtils.createXpp3Node(
						instructionNode, "DynamicImport-Package");
				bundleSymbolicNameNode.setValue(project.getName());
				bundleNameNode.setValue(project.getName());
				if (customMediatorModel.getMediatorClassPackageName() != null
						&& !customMediatorModel.getMediatorClassPackageName()
								.trim().isEmpty()) {
					exportPackageNode.setValue(customMediatorModel.getMediatorClassPackageName());
				} else{
					IJavaProject javaProject = JavaCore.create(project);
					if (null != javaProject) {
						StringBuffer sb = new StringBuffer();
						for (IPackageFragment pkg : javaProject.getPackageFragments()) {
							if (pkg.getKind() == IPackageFragmentRoot.K_SOURCE) {
								if (pkg.hasChildren()) {
									sb.append(pkg.getElementName()).append(",");
								}
							}
						}
						exportPackageNode.setValue(sb.toString().replaceAll(",$", ""));
					}
				}
				dynamicImportNode.setValue("*");
				configurationNode.addChild(instructionNode);
				MavenUtils.saveMavenProject(mavenProject, pomfile);
			}

			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			ProjectUtils.addNatureToProject(project,false,
			                       MEDIATOR_PROJECT_NATURE);
			ProjectUtils.addNatureToProject(project,false,
			        MEDIATOR_PROJECT_MAVEN_CONFIGURED_NATURE);
			ProjectUtils.addBuildSpecificationsToProject(project, new String[]{"org.eclipse.jdt.core.javabuilder", "org.eclipse.m2e.core.maven2Builder"});
			MavenUtils
			.updateWithMavenEclipsePlugin(
					pomfile,
					new String[] { JDT_BUILD_COMMAND },
					new String[] {
							MEDIATOR_PROJECT_NATURE,
							JDT_PROJECT_NATURE });
			customMediatorModel.addToWorkingSet(project);
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			refreshDistProjects();
	    } catch (CoreException e) {
			 log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	    
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (!ESB_GRAPHICAL_PERSPECTIVE.equals(window.getActivePage().getPerspective().getId())) {
					try {
						PlatformUI.getWorkbench().showPerspective(ESB_GRAPHICAL_PERSPECTIVE, window);
						ProjectPresentation.setHierarchicalProjectPresentation();
					} catch (Exception e) {
						log.error("Cannot switch to ESB Graphical Perspective", e);
					}
				}
			}
		});
		return true;
	}

	public void setCustomMediatorModel(CustomMediatorModel customMediatorModel) {
		this.customMediatorModel = customMediatorModel;
	}

	public CustomMediatorModel getCustomMediatorModel() {
		return customMediatorModel;
	}

	
	public IResource getCreatedResource() {
		return project;
	}
	
	// FIXME - need to get these lib from project scope now
	private void addDependancies(IProject project) throws JavaModelException {
 
			String[] depedencyList1 = getDepandanceyList();
			for (String libName : depedencyList1) {
				File dependencyPath = LibraryUtils.getDependencyPath(libName);
				if(dependencyPath!=null){
					JavaUtils.addJarLibraryToProject(project,
							dependencyPath);
				}
			}
		 
	}
	
	//Fixing DEVTOOLEI-1117 and DEVTOOLEI-1119
	private String[] getDepandanceyList() {
		String[] depedencyList = new String[] { LibraryUtils.axis2_1_6_1_wso2vXX_jar,
				LibraryUtils.commons_loggingwso2vXX_jar, LibraryUtils.synapse_core_2_1_7_wso2vXX_jar };
		return depedencyList;
	}
}
