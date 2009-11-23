package com.google.gwt.user.rebind.rpc;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Generator for producing the asynchronous version of a
 * {@link com.google.gwt.user.client.rpc.RemoteService RemoteService} interface.
 */
public class CrossOriginServiceInterfaceProxyGenerator extends Generator {

    @Override
    public String generate(TreeLogger logger, GeneratorContext ctx,
	    String requestedClass) throws UnableToCompleteException {

	TypeOracle typeOracle = ctx.getTypeOracle();
	assert (typeOracle != null);

	JClassType remoteService = typeOracle.findType(requestedClass);
	if (remoteService == null) {
	    logger.log(TreeLogger.ERROR, "Unable to find metadata for type '"
		    + requestedClass + "'", null);
	    throw new UnableToCompleteException();
	}

	if (remoteService.isInterface() == null) {
	    logger.log(TreeLogger.ERROR, remoteService.getQualifiedSourceName()
		    + " is not an interface", null);
	    throw new UnableToCompleteException();
	}

	CrossOriginProxyCreator proxyCreator = createProxyCreator(remoteService);

	TreeLogger proxyLogger = logger.branch(TreeLogger.DEBUG,
		"Generating client proxy for remote service interface '"
		+ remoteService.getQualifiedSourceName() + "'", null);

	return proxyCreator.create(proxyLogger, ctx);
    }

    protected CrossOriginProxyCreator createProxyCreator(JClassType remoteService) {
	return new CrossOriginProxyCreator(remoteService);
    }
}