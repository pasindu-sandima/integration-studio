package org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.wso2.developerstudio.eclipse.gmf.esb.InputConnector;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.AbstractInputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.policies.SequencesInputConnectorItemSemanticEditPolicy;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.providers.EsbElementTypes;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.utils.ImageHolder;

/**
 * @generated NOT
 */
public class SequencesInputConnectorEditPart extends AbstractInputConnectorEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3616;

    /**
     * @generated
     */
    protected IFigure contentPane;

    /**
     * @generated
     */
    protected IFigure primaryShape;

    /**
     * @generated
     */
    public SequencesInputConnectorEditPart(View view) {
        super(view);
    }

    public void activate() {
        super.activate();
        toggleVisibility((InputConnector) ((Node) this.getModel()).getElement());
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, getPrimaryDragEditPolicy());
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SequencesInputConnectorItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable
        // editpolicies
        removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    public NodeFigure getNodeFigure() {
        return super.getNodeFigure();
    }

    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        if (notification.getNotifier() instanceof InputConnector) {
            toggleVisibility((InputConnector) notification.getNotifier());
        }
    }

    public void toggleVisibility(InputConnector inputConnector) {
        boolean isEndpointcountZero = false;
        for (Object child : this.getParent().getChildren()) {
            if (child instanceof MediatorFlow5EditPart) {
                if (((MediatorFlowMediatorFlowCompartment5EditPart) ((MediatorFlow5EditPart) child).getChildren()
                        .get(0)).borderedNodeFigure.getBorderItemContainer().getChildren().size() == 0) {
                    isEndpointcountZero = true;
                } else {
                    isEndpointcountZero = false;
                }
            }
        }
        if ((inputConnector.getIncomingLinks().size() != 0) || (!isEndpointcountZero)) {
            /*
             * This will remove the arrow head of output connector if it is
             * connected to any other input connector.
             */
            NodeFigure figureInput = this.getNodeFigure();
            figureInput.removeAll();
            Figure emptyFigure = new Figure();
            figureInput.add(emptyFigure);
        } else {

            /*
             * This will add the arrow head of output connector if it is not
             * connected to any other input connector.
             */
            NodeFigure figureInput = this.getNodeFigure();
            figureInput.removeAll();
            figureInput.add(createNodeShape());
        }
    }

    /**
     * @generated
     */
    protected IFigure createNodeShape() {
        return primaryShape = new EastPointerFigure();
    }

    /**
     * @generated
     */
    public EastPointerFigure getPrimaryShape() {
        return (EastPointerFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(12, 10);

        // FIXME: workaround for #154536
        result.getBounds().setSize(result.getPreferredSize());
        return result;
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */
    protected NodeFigure createNodeFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane.
     * Respects layout one may have set for generated figure.
     * 
     * @param nodeShape instance of generated figure class
     * @generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @generated
     */
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @generated
     */
    protected void setForegroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setForegroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setBackgroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setBackgroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setLineWidth(int width) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineWidth(width);
        }
    }

    /**
     * @generated
     */
    protected void setLineType(int style) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineStyle(style);
        }
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnTarget() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(EsbElementTypes.EsbLink_4001);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForSource(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == EsbElementTypes.EsbLink_4001) {
            types.add(EsbElementTypes.ProxyOutputConnector_3002);
            types.add(EsbElementTypes.PropertyMediatorOutputConnector_3034);
            types.add(EsbElementTypes.PropertyGroupMediatorOutputConnector_3790);
            types.add(EsbElementTypes.ThrottleMediatorOutputConnector_3122);
            types.add(EsbElementTypes.ThrottleMediatorOnAcceptOutputConnector_3581);
            types.add(EsbElementTypes.ThrottleMediatorOnRejectOutputConnector_3582);
            types.add(EsbElementTypes.FilterMediatorOutputConnector_3534);
            types.add(EsbElementTypes.FilterMediatorPassOutputConnector_3011);
            types.add(EsbElementTypes.FilterMediatorFailOutputConnector_3012);
            types.add(EsbElementTypes.LogMediatorOutputConnector_3019);
            types.add(EsbElementTypes.EnrichMediatorOutputConnector_3037);
            types.add(EsbElementTypes.XSLTMediatorOutputConnector_3040);
            types.add(EsbElementTypes.SwitchCaseBranchOutputConnector_3043);
            types.add(EsbElementTypes.SwitchDefaultBranchOutputConnector_3044);
            types.add(EsbElementTypes.SwitchMediatorOutputConnector_3499);
            types.add(EsbElementTypes.SequenceOutputConnector_3050);
            types.add(EsbElementTypes.EventMediatorOutputConnector_3053);
            types.add(EsbElementTypes.EntitlementMediatorOutputConnector_3056);
            types.add(EsbElementTypes.ClassMediatorOutputConnector_3059);
            types.add(EsbElementTypes.SpringMediatorOutputConnector_3062);
            types.add(EsbElementTypes.ScriptMediatorOutputConnector_3065);
            types.add(EsbElementTypes.FaultMediatorOutputConnector_3068);
            types.add(EsbElementTypes.XQueryMediatorOutputConnector_3071);
            types.add(EsbElementTypes.CommandMediatorOutputConnector_3074);
            types.add(EsbElementTypes.DBLookupMediatorOutputConnector_3077);
            types.add(EsbElementTypes.DBReportMediatorOutputConnector_3080);
            types.add(EsbElementTypes.SmooksMediatorOutputConnector_3083);
            types.add(EsbElementTypes.SendMediatorOutputConnector_3086);
            types.add(EsbElementTypes.SendMediatorEndpointOutputConnector_3539);
            types.add(EsbElementTypes.HeaderMediatorOutputConnector_3101);
            types.add(EsbElementTypes.CloneMediatorOutputConnector_3104);
            types.add(EsbElementTypes.CloneMediatorTargetOutputConnector_3133);
            types.add(EsbElementTypes.CacheMediatorOutputConnector_3107);
            types.add(EsbElementTypes.IterateMediatorOutputConnector_3110);
            types.add(EsbElementTypes.IterateMediatorTargetOutputConnector_3606);
            types.add(EsbElementTypes.CalloutMediatorOutputConnector_3116);
            types.add(EsbElementTypes.TransactionMediatorOutputConnector_3119);
            types.add(EsbElementTypes.RMSequenceMediatorOutputConnector_3125);
            types.add(EsbElementTypes.RuleMediatorOutputConnector_3128);
            types.add(EsbElementTypes.OAuthMediatorOutputConnector_3131);
            types.add(EsbElementTypes.AggregateMediatorOutputConnector_3113);
            types.add(EsbElementTypes.AggregateMediatorOnCompleteOutputConnector_3132);
            types.add(EsbElementTypes.StoreMediatorOutputConnector_3590);
            types.add(EsbElementTypes.BuilderMediatorOutputConector_3593);
            types.add(EsbElementTypes.CallTemplateMediatorOutputConnector_3596);
            types.add(EsbElementTypes.PayloadFactoryMediatorOutputConnector_3599);
            types.add(EsbElementTypes.EnqueueMediatorOutputConnector_3602);
            types.add(EsbElementTypes.DefaultEndPointOutputConnector_3022);
            types.add(EsbElementTypes.AddressEndPointOutputConnector_3031);
            types.add(EsbElementTypes.FailoverEndPointOutputConnector_3090);
            types.add(EsbElementTypes.FailoverEndPointWestOutputConnector_3097);
            types.add(EsbElementTypes.WSDLEndPointOutputConnector_3093);
            types.add(EsbElementTypes.LoadBalanceEndPointOutputConnector_3096);
            types.add(EsbElementTypes.LoadBalanceEndPointWestOutputConnector_3098);
            types.add(EsbElementTypes.MessageOutputConnector_3047);
            types.add(EsbElementTypes.MergeNodeOutputConnector_3016);
            types.add(EsbElementTypes.SequencesOutputConnector_3617);
            types.add(EsbElementTypes.JsonTransformMediatorOutputConnector_3793);
        }
        return types;
    }

    /**
     * @generated NOT
     */
    public class EastPointerFigure extends RoundedRectangle {

        /**
         * @generated NOT
         */
        public EastPointerFigure() {

            /*
             * this.setBackgroundColor(THIS_BACK);
             * this.setPreferredSize(new Dimension(getMapMode().DPtoLP(12),
             * getMapMode().DPtoLP(10)));
             */

            GridLayout layoutThis = new GridLayout();
            layoutThis.numColumns = 1;
            layoutThis.makeColumnsEqualWidth = true;
            layoutThis.marginHeight = 0;
            layoutThis.marginWidth = 0;
            this.setLayoutManager(layoutThis);

            this.setCornerDimensions(new Dimension(1, 1));
            this.setFill(false);
            this.setOutline(false);
            // this.setBackgroundColor(get);
            this.setPreferredSize(new Dimension(22, 18));
            createContents();
        }

        public void createContents() {
            GridData constraintImageRectangle11 = new GridData();
            constraintImageRectangle11.verticalAlignment = GridData.FILL;
            constraintImageRectangle11.horizontalAlignment = GridData.FILL;
            constraintImageRectangle11.horizontalIndent = 0;
            constraintImageRectangle11.horizontalSpan = 1;
            constraintImageRectangle11.verticalSpan = 2;
            constraintImageRectangle11.grabExcessHorizontalSpace = true;
            constraintImageRectangle11.grabExcessVerticalSpace = true;

            ImageFigure img1 = new ImageFigure(ImageHolder.getInstance().getArrowEastImage());
            img1.setSize(new Dimension(22, 18));

            RectangleFigure imageRectangle11 = new RectangleFigure();
            imageRectangle11.setOutline(false);
            imageRectangle11.setBackgroundColor(new Color(null, 255, 255, 255));
            imageRectangle11.setPreferredSize(new Dimension(22, 18));
            imageRectangle11.add(img1);

            this.add(imageRectangle11, constraintImageRectangle11);
        }
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * @generated
     */
    static final Color THIS_BACK = new Color(null, 50, 50, 50);

}
