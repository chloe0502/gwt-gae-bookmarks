package com.google.gwt.user.rebind.rpc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Associates a {@link RemoteService} with a base path. This annotation will
 * cause the client-side proxy to automatically invoke the
 * {@link ServiceDefTarget#setServiceEntryPoint(String)} method with
 * <code>{@link com.google.gwt.core.client.GWT#getModuleBaseURL() GWT.getModuleBaseURL()} + {@link RemoteServiceBasePath#value()}</code>
 * as its argument. Subsequent calls to
 * {@link ServiceDefTarget#setServiceEntryPoint(String)} will override this
 * default path.
 */
@Documented
@Target(ElementType.TYPE)
public @interface RemoteServiceBasePath {
  /**
   * The base path for the {@link RemoteService} implementation.
   * 
   * @return base path for the {@link RemoteService} implementation
   */
  String value();
}