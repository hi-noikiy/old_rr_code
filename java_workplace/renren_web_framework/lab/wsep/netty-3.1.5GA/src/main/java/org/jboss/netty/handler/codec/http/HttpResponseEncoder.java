/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.http;

import static org.jboss.netty.handler.codec.http.HttpCodecUtil.*;

import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Encodes an {@link HttpResponse} or an {@link HttpChunk} into
 * a {@link ChannelBuffer}.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Andy Taylor (andy.taylor@jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 * @version $Rev: 1685 $, $Date: 2009-08-28 16:15:49 +0900 (금, 28 8 2009) $
 */
public class HttpResponseEncoder extends HttpMessageEncoder {

    /**
     * Creates a new instance.
     */
    public HttpResponseEncoder() {
        super();
    }

    @Override
    protected void encodeInitialLine(ChannelBuffer buf, HttpMessage message) {
        HttpResponse response = (HttpResponse) message;
        try {
            buf.writeBytes(response.getProtocolVersion().toString().getBytes("ASCII"));
            buf.writeByte(SP);
            buf.writeBytes(String.valueOf(response.getStatus().getCode()).getBytes("ASCII"));
            buf.writeByte(SP);
            buf.writeBytes(String.valueOf(response.getStatus().getReasonPhrase()).getBytes("ASCII"));
            buf.writeBytes(CRLF);
        } catch (UnsupportedEncodingException e) {
            throw (Error) new Error().initCause(e);
        }
    }
}