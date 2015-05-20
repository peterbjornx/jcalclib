package nl.peterbjornx.calclib.bltin;

import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.var.ConstantVariable;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class PhysicalConstants {
    public final ConstantVariable SPEED_OF_LIGHT       = new ConstantVariable(NumberType.TYPE, 2.99792458e8);
    public final ConstantVariable GRAVITATIONAL_CONST  = new ConstantVariable(NumberType.TYPE, 6.6726e-11);
    public final ConstantVariable STANDARD_PRESSURE    = new ConstantVariable(NumberType.TYPE, 1.01325e5);
    public final ConstantVariable MOLAR_VOLUME_TSPS    = new ConstantVariable(NumberType.TYPE, 2.24141e-2);
    public final ConstantVariable CELSIUS_OFFSET       = new ConstantVariable(NumberType.TYPE, 273.15);
    public final ConstantVariable AVOGADRO_NUMBER      = new ConstantVariable(NumberType.TYPE, 6.02214e23);
    public final ConstantVariable GAS_CONSTANT         = new ConstantVariable(NumberType.TYPE, 8.3145);
    public final ConstantVariable BOLTZMANN_CONST      = new ConstantVariable(NumberType.TYPE, 1.38066e-23);
    public final ConstantVariable WIEN_CONST           = new ConstantVariable(NumberType.TYPE, 2.8978e-3);
    public final ConstantVariable PLANCK_CONST         = new ConstantVariable(NumberType.TYPE, 6.62607e-34);
    public final ConstantVariable ELECTRIC_CONST       = new ConstantVariable(NumberType.TYPE, 8.85419e-12);
    public final ConstantVariable COULOMB_CONST        = new ConstantVariable(NumberType.TYPE, 8.98755e9);
    public final ConstantVariable VACUUM_PERMEABILITY  = new ConstantVariable(NumberType.TYPE, 1.25664e-6);
    public final ConstantVariable CHARGE_QUANTUM       = new ConstantVariable(NumberType.TYPE, 1.6021765e-19);
    public final ConstantVariable FARADAY_CONST        = new ConstantVariable(NumberType.TYPE, 9.64853e4);
    public final ConstantVariable HYDROGEN_BOHR_RADIUS = new ConstantVariable(NumberType.TYPE, 5.29177e-11);
    public final ConstantVariable HYDROGEN_RYDBERG     = new ConstantVariable(NumberType.TYPE, 1.0968e7);
    public final ConstantVariable ATOMIC_MASS_UNIT     = new ConstantVariable(NumberType.TYPE, 1.66054e-27);

}
