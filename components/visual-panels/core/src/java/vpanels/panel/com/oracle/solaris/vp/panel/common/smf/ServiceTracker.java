/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
 * or http://www.opensolaris.org/os/licensing.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at usr/src/OPENSOLARIS.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 */

package com.oracle.solaris.vp.panel.common.smf;

import java.beans.PropertyChangeEvent;
import com.oracle.solaris.rad.client.ADRName;
import com.oracle.solaris.vp.panel.common.api.smf_old.ServiceInfo;
import com.oracle.solaris.vp.panel.common.*;

/**
 * The {@code ServiceTracker} is a {@link BeanTracker} that automatically
 * creates and re-creates an {@link #getService AggregatedRefreshService}, which
 * wraps a {@link ServiceBeanAdaptor}, which wraps a {@link
 * ServiceInfo}.
 */
public class ServiceTracker extends BeanTracker<ServiceInfo>
    implements HasService {

    //
    // Static data
    //

    /**
     * The name of the property that changes with {@link #setService}.
     */
    public static final String PROPERTY_SERVICE = "service";

    //
    // Instance data
    //

    private AggregatedRefreshService service;

    //
    // Constructors
    //

    public ServiceTracker(ADRName oName, ClientContext context)
	throws TrackerException {

	super(oName, ServiceInfo.class, context);
    }

    public ServiceTracker(String serviceName, String instanceName,
	ClientContext context) throws TrackerException {

	this(ServiceUtil.toADRName(serviceName, instanceName), context);
    }

    //
    // HasService methods
    //

    @Override
    public AggregatedRefreshService getService() {
	return service;
    }

    //
    // BeanTracker methods
    //

    @Override
    public void setBean(ServiceInfo bean) {
	if (getBean() != bean) {
	    super.setBean(bean);
	    setService();
	}
    }

    //
    // ServiceTracker methods
    //

    public void setObjectName(String serviceName, String instanceName)
        throws TrackerException {

	ADRName oName = ServiceUtil.toADRName(serviceName, instanceName);
	setObjectName(oName);
    }

    protected void setService(AggregatedRefreshService service) {
	if (this.service != service) {
	    PropertyChangeEvent e = new PropertyChangeEvent(
		this, PROPERTY_SERVICE, this.service, service);
	    this.service = service;
	    getPropertyChangeListeners().propertyChange(e);
	}
    }

    //
    // Private methods
    //

    private void setService() {
	setService(new AggregatedRefreshService(new ServiceBeanAdaptor(
	    getBean())));
    }
}
