/*
 * This file is part of fabric-loom, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016-2022 FabricMC
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

package net.fabricmc.loom.api.mappings.layered;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

/**
 * The standard namespaces used by loom.
 */
public enum MappingsNamespace {
	/**
	 * Official mappings are the names that are used in the vanilla Minecraft game jars, these are usually obfuscated.
	 */
	OFFICIAL,

	/**
	 * Intermediary mappings have been generated to provide a stable set of names across minecraft versions.
	 *
	 * <p>Intermediary is used in a production runtime (outside a dev env) allowing mods to run across multiple versions of the game. Mods are remapped from "named" at build time.
	 *
	 * @see <a href="https://github.com/FabricMC/intermediary/">github.com/FabricMC/intermediary/</a>
	 */
	INTERMEDIARY,

	/**
	 * SRG mappings provided by Minecraft Forge.
	 *
	 * <p>They are used as the mapping set in a Forge production environment akin to Fabric's
	 * {@linkplain #INTERMEDIARY intermediary mappings}.
	 */
	SRG,

	/**
	 * Mojang's official names from their deobfuscation maps.
	 *
	 * <p>They are used as the mapping set in a NeoForge production environment akin to Fabric's
	 * {@linkplain #INTERMEDIARY intermediary mappings}.
	 */
	MOJANG,

	/**
	 * Named mappings are the developer friendly names used to develop mods against.
	 */
	NAMED;

	/**
	 * Gets a {@code MappingsNamespace} from a namespace string.
	 *
	 * @param namespace the name of the namespace as a lowercase string
	 * @return the {@code MappingsNamespace}, or null if not found
	 */
	public static @Nullable MappingsNamespace of(String namespace) {
		return switch (namespace) {
		case "official" -> OFFICIAL;
		case "intermediary" -> INTERMEDIARY;
		case "srg" -> SRG;
		case "mojang" -> MOJANG;
		case "named" -> NAMED;
		default -> null;
		};
	}

	@Override
	public String toString() {
		return name().toLowerCase(Locale.ROOT);
	}
}
