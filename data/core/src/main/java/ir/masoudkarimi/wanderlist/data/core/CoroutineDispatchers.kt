package ir.masoudkarimi.wanderlist.data.core

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
  val main: CoroutineDispatcher
  val io: CoroutineDispatcher
  val default: CoroutineDispatcher
}

internal data class DefaultCoroutineDispatchers(
  override val main: CoroutineDispatcher,
  override val io: CoroutineDispatcher,
  override val default: CoroutineDispatcher,
) : CoroutineDispatchers