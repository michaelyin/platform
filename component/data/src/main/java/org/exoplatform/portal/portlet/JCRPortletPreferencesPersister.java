/***************************************************************************
 * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package org.exoplatform.portal.portlet;

import javax.jcr.Node;

import org.apache.commons.logging.Log;
import org.exoplatform.portal.config.Data;
import org.exoplatform.portal.config.JCRDataService;
import org.exoplatform.services.log.LogService;
import org.exoplatform.services.portletcontainer.pci.ExoWindowID;
import org.exoplatform.services.portletcontainer.pci.WindowID;
import org.exoplatform.services.portletcontainer.pci.model.ExoPortletPreferences;
import org.exoplatform.services.portletcontainer.persistence.PortletPreferencesPersister;
/**
 * Jul 16, 2004 
 * @author: Tuan Nguyen
 * @email:   tuan08@users.sourceforge.net
 * @version: $Id: PortletPreferencesPersisterImpl.java,v 1.4 2004/08/11 02:22:16 tuan08 Exp $
 */
public class JCRPortletPreferencesPersister extends JCRDataService implements PortletPreferencesPersister {
  
  @SuppressWarnings("unused")
  private transient Log log_;
  
  public JCRPortletPreferencesPersister(LogService lservice) {
    log_ = lservice.getLog(getClass()); 
  }

  public ExoPortletPreferences getPortletPreferences(WindowID windowID) throws Exception {
    Node parentNode = getDataServiceNode(windowID.getOwner(), PORTLE_TPREFERENCES, false);
    if(parentNode == null) return null;
    ExoWindowID exoWindowID = (ExoWindowID) windowID ;    
    Node node = getNode(parentNode, exoWindowID.getPersistenceId());
    if(node == null) return null;
    Data data = nodeToData(node);
    PortletPreferences portletPerferences = 
      (PortletPreferences) fromXML(data.getData(), PortletPreferences.class);
    return  portletPerferences.toExoPortletPreferences() ;
  }
  
  @SuppressWarnings("unused")
  public void savePortletPreferences(WindowID windowID, ExoPortletPreferences exoPref) throws Exception {
  }
  
}