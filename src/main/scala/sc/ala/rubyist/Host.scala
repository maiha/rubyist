package sc.ala.rubyist

import java.net.InetAddress

case class Host(ia:InetAddress) {
  def name    = ia.getHostName
  def address = ia.getHostAddress

  def identity: String = {
    if (name.nonEmpty)
      name
    else if (address.nonEmpty)
      address
    else
      throw new RuntimeException("Hostname not found: " + toString)
  }
}

object CurrentHost extends Host(InetAddress.getLocalHost)

