/*
 * This file is part of ChaosConfig, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 ChaosConfig
 * Copyright (c) 2020 TechReborn
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

package chaosstorage.config;

import reborncore.common.config.Config;

import java.util.Arrays;
import java.util.List;

public class ChaosStorageConfig {

	@Config(config = "upgrade", category = "upgrade", key = "rangeUpgradeUsage", comment = "The additional energy used by the Range Upgrade")
	public static int RangeUpgradeUsage = 8;

	@Config(config = "upgrade", category = "upgrade", key = "speedUpgradeUsage", comment = "The additional energy use by the Speed Upgrade")
	public static int SpeedUpgradeUsage = 2;

	@Config(config = "upgrade", category = "upgrade", key = "craftingUpgradeUsage", comment = "The additional energy used by the Crafting Upgrade")
	public static int CraftingUpgradeUsage = 5;

	@Config(config = "upgrade", category = "upgrade", key = "stackUpgradeUsage", comment = "The additional energy used by the Stack Upgrade")
	public static int StackUpgradeUsage = 12;

	@Config(config = "upgrade", category = "upgrade", key = "silkTouchUpgradeUsage", comment = "The additional energy used by the Silk Touch Upgrade")
	public static int SilkTouchUpgradeUsage = 12;

	@Config(config = "upgrade", category = "upgrade", key = "fortune1UpgradeUsage", comment = "The additional energy used by the Fortune 1 Upgrade")
	public static int Fortune1UpgradeUsage = 10;

	@Config(config = "upgrade", category = "upgrade", key = "fortune2UpgradeUsage", comment = "The additional energy used by the Fortune 2 Upgrade")
	public static int Fortune2UpgradeUsage = 10;

	@Config(config = "upgrade", category = "upgrade", key = "fortune3UpgradeUsage", comment = "The additional energy used by the Fortune 3 Upgrade")
	public static int Fortune3UpgradeUsage = 10;

	@Config(config = "machines", category = "controller", key = "ControllerInput", comment = "Controller Max Input (Value in EU)")
	public static int ControllerMaxInput = Integer.MAX_VALUE;

	@Config(config = "machines", category = "controller", key = "ControllerEnegryPerTick", comment = "Controller Energy useage per tick (Value in EU)")
	public static int ControllerEngergyPerTick = 1;

	@Config(config = "machines", category = "controller", key = "ControllerMaxEnergy", comment = "Energy storage in Controller")
	public static int ControllerMaxPower = 40_000;

	@Config(config = "machines", category = "grid", key = "WirelessGridStoredEnergy", comment = "Energy storage in Wirless Grid")
	public static int WirelessGridStoredPower = 4_000;
}
