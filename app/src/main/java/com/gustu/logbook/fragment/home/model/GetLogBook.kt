package com.gustu.logbook.fragment.home.model

import com.google.gson.annotations.SerializedName

data class GetLogBook(

	@field:SerializedName("RLK_NAMA")
	val rLKNAMA: String? = null,

	@field:SerializedName("TLB_NAMA_KEGIATAN")
	val tLBNAMAKEGIATAN: String? = null,

	@field:SerializedName("TLB_QTY")
	val tLBQTY: String? = null,

	@field:SerializedName("MST_KEG_LOGB_MKL_KODE")
	val mSTKEGLOGBMKLKODE: String? = null,

	@field:SerializedName("RLP_NAMA")
	val rLPNAMA: String? = null,

	@field:SerializedName("MST_PEGAWAI_MPG_KODE")
	val mSTPEGAWAIMPGKODE: String? = null,

	@field:SerializedName("MST_UNIT_MSU_KODE")
	val mSTUNITMSUKODE: String? = null,

	@field:SerializedName("TLB_NAMA_PEGAWAI")
	val tLBNAMAPEGAWAI: String? = null,

	@field:SerializedName("TLB_ID")
	val tLBID: String? = null,

	@field:SerializedName("TLB_KETERANGAN_KEGIATAN")
	val tLBKETERANGANKEGIATAN: String? = null,

	@field:SerializedName("TLB_SOURCE_DATA")
	val tLBSOURCEDATA: String? = null,

	@field:SerializedName("TLB_TANGGAL")
	val tLBTANGGAL: String? = null,

	@field:SerializedName("TLB_TANGGAL_AKHIR")
	val tLBTANGGALAKHIR: String? = null,

	@field:SerializedName("TLB_OUTPUT")
	val tLBOUTPUT: String? = null
)