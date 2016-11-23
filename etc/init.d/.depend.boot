TARGETS = mountkernfs.sh hostname.sh udev mountdevsubfs.sh urandom mountall.sh mountall-bootclean.sh hwclock.sh checkroot.sh networking mountnfs.sh mountnfs-bootclean.sh procps checkfs.sh checkroot-bootclean.sh bootmisc.sh udev-finish kmod
INTERACTIVE = udev checkroot.sh checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
mountnfs.sh: mountall.sh mountall-bootclean.sh networking
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountall.sh mountnfs.sh udev
udev-finish: udev mountall.sh mountall-bootclean.sh
kmod: checkroot.sh
