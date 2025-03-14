trait AbsDomain[t] {
    pub def join(x: t, y: t): t
    pub def meet(x: t, y: t): t
    pub def widen(x: t, y: t): t
}

trait NumericOps[t] {
    pub def plus(x: t, y: t): t
}

instance NumericOps[Int32] {
    pub def plus(x: Int32, y: Int32): Int32 = x + y
}

enum Interval[t] with ToString {
    case Top
    case UpperLower(t, t)
    case Bott
}

def plus(x: Interval[t], y: Interval[t]): Interval[t] with NumericOps[t] =
    match (x, y) {
        case (Interval.Top, _) => Interval.Top
        case (_, Interval.Top) => Interval.Top
        case (Interval.Bott, _) => Interval.Bott
        case (_, Interval.Bott) => Interval.Bott
        case (Interval.UpperLower(x1, x2), Interval.UpperLower(y1, y2)) =>
               // TODO: Need a way to convert infinite values to Top
               Interval.UpperLower(
                 NumericOps.plus(x1, y1),
                 NumericOps.plus(x2, y2)
               )    
    }



// instance AbsDomain[Interval] {

//     pub def join(x: Interval, y: Interval): Interval = match (x, y) {
//         case 
//     }

//     pub def meet(x: Interval, y: Interval): Interval = ???

//     pub def widen(x: Interval, y: Interval): Interval = ???

// }




    
// The main entry point.
def main(): Unit \ IO =
    let x = Interval.UpperLower(3, 3);
    let y = Interval.UpperLower(3, 3);
    let z = plus(x, y);
    println(z)
