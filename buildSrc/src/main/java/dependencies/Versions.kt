package dependencies

object Versions {
    const val androidCompileSdkVersion = 32
    const val androidMinSdkVersion = 23
    const val androidTargetSdkVersion = 32

    private const val versionMajor = 0
    private const val versionMinor = 1
    private const val versionPatch = 1
    const val androidVersionCode = (versionMajor * 100 + versionMinor * 10 + versionPatch )

    const val androidVersionName = "$versionMajor.$versionMinor.$versionPatch"
}
