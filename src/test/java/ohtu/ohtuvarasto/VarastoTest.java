package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2OikeaSaldo() {
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastolla2OikeaTilavuus() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegaVarastoa() {
        Varasto virheellinen = new Varasto(-1);
        assertEquals(0, virheellinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2EiLuoNegaVarastoa() {
        Varasto virheellinen = new Varasto(-1, 0);
        assertEquals(0, virheellinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2EiLuoNegaSaldoa() {
        Varasto virheellinen = new Varasto(5, -1);
        assertEquals(0, virheellinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2EiYlitaTilavuutta() {
        Varasto virheellinen = new Varasto(1, 5);
        assertEquals(1, virheellinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void NegaLisaysEiLisaaSaldoa() {
        varasto2.lisaaVarastoon(-1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void YliLisaysEiYlitaTilavuutta() {
        varasto2.lisaaVarastoon(15);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void NegaOttaminenEiPalauta() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liikaOttaminenPalauttaaLoput() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(15);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        Varasto v = new Varasto(10, 2);
        assertTrue("saldo = 2.0, vielä tilaa 8.0".equals(v.toString()));
    }

}
