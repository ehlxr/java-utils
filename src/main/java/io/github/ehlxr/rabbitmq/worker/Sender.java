/*
 * The MIT License (MIT)
 *
 * Copyright © 2020 xrv <xrg@live.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.ehlxr.rabbitmq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.github.ehlxr.rabbitmq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * work 模式：一个生产者，多个消费者，每个消费者获取到的消息唯一
 *
 * @author ehlxr
 * @since 2019-01-22.
 */
public class Sender {
    private final static String QUEUE_NAME = "queue_work";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            String message = "冬马小三" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}
