/*
 * This file is part of fabric-loom, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2023 FabricMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.fabricmc.loom.test.unit.architectury

import dev.architectury.loom.metadata.ArchitecturyCommonJson
import spock.lang.Specification

class ArchitecturyCommonJsonTest extends Specification {
    def "read access widener"() {
        given:
            def acj = ArchitecturyCommonJson.of(jsonText)
        when:
            def accessWidenerName = acj.accessWidener
        then:
            accessWidenerName == expectedAw
        where:
            jsonText                                | expectedAw
            '{}'                                    | null
            '{"accessWidener":"foo.accesswidener"}' | 'foo.accesswidener'
    }

    def "read injected interfaces"() {
        given:
            def acj = ArchitecturyCommonJson.of(jsonText)
        when:
            def injectedInterfaces = acj.getInjectedInterfaces('foo')
            Map<String, List<String>> itfMap = [:]
            for (def entry : injectedInterfaces) {
                itfMap.computeIfAbsent(entry.className()) { [] }.add(entry.ifaceName())
            }
        then:
            itfMap == expected
        where:
            jsonText | expected
            '{}' | [:]
            '{"injected_interfaces":{"target/class/Here":["my/Interface","another/Itf"]}}' | ['target/class/Here': ['my/Interface', 'another/Itf']]
    }
}
