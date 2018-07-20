package MyRpc

class Pait[T <: Comparable[T]] {
  def bigger(first: T, second: T): Unit = {
    if (first.compareTo(second) > 0) first else second
  }
}
