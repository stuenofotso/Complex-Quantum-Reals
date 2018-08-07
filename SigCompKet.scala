package compket

import org.apache.commons.math.complex.Complex
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution


/**
  * Created by steve on 02/08/2018.
  */


class ComplexQbClass(real:Signal[Double], imaginary:Signal[Double], pendings:Set[ComplexQbClass]){


  def getReal: Signal[Double] = real
  def getImaginary: Signal[Double] = imaginary
  def getPendings: Set[ComplexQbClass] = pendings


  def degenerate(): QbComplex = {
    if(real().isNaN || imaginary().isNaN ){
      this.pendings.foreach(x=>x.degenerate())
    }

    println(toString)

    QbComplex(new Complex(real(), imaginary()))

  }


  def +(that: ComplexQbClass): ComplexQbClass = new ComplexQbClass(Signal{real()+that.getReal()}, Signal{imaginary()+that.getImaginary()}, this.pendings++that.getPendings++Set(this, that))

  def -(that: ComplexQbClass): ComplexQbClass = new  ComplexQbClass(Signal{real()-that.getReal()}, Signal{imaginary()-that.getImaginary()}, this.pendings++that.getPendings++Set(this, that))

  def *(that: ComplexQbClass): ComplexQbClass = new  ComplexQbClass(Signal{real()*that.getReal()-imaginary()*that.getImaginary()}, Signal{real()*that.getImaginary()+imaginary()*that.getReal()}, this.pendings++that.getPendings++Set(this, that))


  override def toString: String = {
    real()+" + i"+imaginary()+ {
      if (real().isNaN || imaginary().isNaN)
        " (results not available since a part of the computation is not degenerated. Please, if you want to degenerate all quantums,  call the degenerate() method)"
      else ""
    }

  }


}




final case class QbComplex(value: Complex) extends ComplexQbClass(real = Signal{value.getReal},imaginary=Signal{value.getImaginary}, Set()) {

  def this(real: Double, imaginary: Double) = this(new Complex(real, imaginary))

  override def toString: String = value.getReal+" + i"+value.getImaginary

  override def degenerate(): QbComplex = {
    println(toString)
    this
  }
}


final case class  CompKetClass(x:Double, y: Double, p: Array[Array[Double]]) extends ComplexQbClass(real = Signal{Double.NaN}, imaginary = Signal{Double.NaN}, Set()) {
  require(p.length==2 && p.forall(_.length==2), "the probability matrice must be of size 2*2")
  require(p.forall(_.sum==1.0), "the sum of probabilities of each line must be equal to 1 "+p.toList.map(_.toList))


  override def degenerate(): QbComplex =  {
    val real:Signal[Double] = getReal
    val imaginary:Signal[Double] = getImaginary

    if(real().isNaN || imaginary().isNaN ) {

      real() = this.x * new EnumeratedIntegerDistribution(Array(0, 1), p(0)).sample()
      imaginary() = y * new EnumeratedIntegerDistribution(Array(0, 1), p(1)).sample()
    }

    println(toString)

    QbComplex(new Complex(real(), imaginary()))
  }


  override def toString: String = {
    val real:Signal[Double] = getReal
    val imaginary:Signal[Double] = getImaginary

    if(real().isNaN || imaginary().isNaN ) {
      if(x==0 && y == 0) "0" else (if(x!=0)  x + " (" + "%.2f".format(p(0)(0)) + "|0> + " + "%.2f".format(p(0)(1)) + "|1>)"+(if(y!=0) " + " else "") else "")+(if(y!=0) "i"+y+"("+"%.2f".format(p(1)(0))+"|0> + "+"%.2f".format(p(1)(1))+"|1>)" else "")
    }
    else{
      real()+" + i"+imaginary()
    }


  }




}



object SigCompKet {


  def run(): Unit = {
    val a = CompKetClass(1, 2, Array(Array(0.4, 0.6), Array(0.5, 0.5)))
    val b = CompKetClass(1.1, 2.1, Array(Array(0.12, 0.88), Array(0.22, 0.78)))
    val sum: ComplexQbClass = a+b

    println("a = "+a)
    println("b = "+b)
    println("sum = "+sum)

    //val ar = a.degenerate()
    //val br = b.degenerate()
    val sumr = sum.degenerate()

    println("a = "+a)
    println("b = "+b)
    println("sum = "+sum)
  }

  run()

}
