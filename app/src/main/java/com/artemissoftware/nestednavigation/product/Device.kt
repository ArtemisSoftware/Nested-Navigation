package com.artemissoftware.nestednavigation.product

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize


open class Device(val id: String, val name: String)

//class AssetParamType : NavType<Device>(isNullableAllowed = false) {
//    override fun get(bundle: Bundle, key: String): Device? {
//        return bundle.getParcelable(key)
//    }
//
//    override fun parseValue(value: String): Device {
//        return Gson().fromJson(value, Device::class.java)
//    }
//
//    override fun put(bundle: Bundle, key: String, value: Device) {
//        bundle.putParcelable(key, value)
//    }
//}

@Parcelize
data class DeviceV2(val idd: String, val named: String) : Device(idd, named), Parcelable

class AssetParamTypeV2 : NavType<DeviceV2>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): DeviceV2? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): DeviceV2 {
        return Gson().fromJson(value, DeviceV2::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: DeviceV2) {
        bundle.putParcelable(key, value)
    }
}