package org.quaerense.spacewanderers.domain.state

import java.io.Serializable

sealed class DownloadState: Serializable

object Pending : DownloadState()
class Loading(val downloadingPercent: Int) : DownloadState()
object Failed : DownloadState()
object Restarting : DownloadState()
object Succeeded : DownloadState()
