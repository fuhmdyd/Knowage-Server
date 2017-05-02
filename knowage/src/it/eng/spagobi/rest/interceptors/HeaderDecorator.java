/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.
 * 
 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.spagobi.rest.interceptors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

/**
 * @author Marco Cortella (marco.cortella@eng.it)
 * 
 *         This interceptor works on REST Services responses and force the utf-8
 *         charset in the Http Content-type header and in the context Media Type
 */
@Provider
@ServerInterceptor
public class HeaderDecorator implements MessageBodyWriterInterceptor {

	public void write(MessageBodyWriterContext context) throws IOException, WebApplicationException {

		MultivaluedMap<String, Object> map = context.getHeaders();

		List<Object> currentContentTypes = map.get("Content-Type");
		if (currentContentTypes != null) {
			MediaType currentContentType = (MediaType) currentContentTypes.get(0);
			String contentType = currentContentType.getType();
			String contentSubType = currentContentType.getSubtype();
			String allContentType = contentType;
			if (contentSubType != null) {
				allContentType = contentType + "/" + contentSubType;
			}
			// add new header with charset forced to utf-8
			context.getHeaders().add("Content-Type", allContentType + "; charset=utf-8");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("charset", "UTF-8");
			MediaType mt = new MediaType(contentType, contentSubType, parameters);
			// set media type with utf-8 for correct encoding of content
			context.setMediaType(mt);

			context.proceed();
		}

	}

}