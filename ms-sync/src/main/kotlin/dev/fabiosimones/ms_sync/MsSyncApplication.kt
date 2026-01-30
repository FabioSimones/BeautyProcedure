package dev.fabiosimones.ms_sync

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsSyncApplication

fun main(args: Array<String>) {
	runApplication<MsSyncApplication>(*args)
}
