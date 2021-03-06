/*
 * Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * U.S. Government Rights - Commercial software. Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and applicable
 * provisions of the FAR and its supplements.
 *
 *
 * This distribution may include materials developed by third parties. Sun,
 * Sun Microsystems, the Sun logo and Solaris are trademarks or registered
 * trademarks of Sun Microsystems, Inc. in the U.S. and other countries.
 *
 */

/*
 * Note: this file originally auto-generated by mib2c using
 *        : mib2c.scalar.conf,v 1.5 2002/07/18 14:18:52 dts12 Exp $
 */

#ifndef demo_module_10_H
#define demo_module_10_H

/* function declarations */
void init_demo_module_10(void);

void collect_data(void);
extern int sleep(int);

Netsnmp_Node_Handler get_longRunScalar;
Netsnmp_Node_Handler delayed_instance_handler;
Netsnmp_Node_Handler get_timestamp;
SNMPAlarmCallback get_status;


#endif /* demo_module_10_H */
