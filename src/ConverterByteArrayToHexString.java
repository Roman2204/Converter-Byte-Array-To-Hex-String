import java.math.BigInteger;    // For Method 3
import java.io.UnsupportedEncodingException;    // For Method 5

public class ConverterByteArrayToHexString {
    /*
    References:
    1) http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
    2) http://www.rgagnon.com/javadetails/java-0596.html
     */
    ConverterByteArrayToHexString() {}

    /* START Method 1 [1] */
    public static String convertByteArrayToHexString1( byte[] ByteArray ) throws Exception {
        StringBuilder HexString = new StringBuilder(ByteArray.length * 2);
        for ( byte each_byte : ByteArray )
            HexString.append(String.format("%02x", each_byte & 0xFF));
        return HexString.toString();
    }
    /* END Method 1 */

    /* START Method 2 [1] */
    final protected static char[] HexArray = "0123456789ABCDEF".toCharArray();
    public static String convertByteArrayToHexString2( byte[] ByteArray ) throws Exception {
        char[] HexChars = new char[ByteArray.length * 2];
        for ( int j = 0; j < ByteArray.length; j++ )
        {
            int EachHexValue = ByteArray[j] & 0xFF;
            HexChars[j * 2] = HexArray[EachHexValue >>> 4];
            HexChars[j * 2 + 1] = HexArray[EachHexValue & 0x0F];
        }
        return new String(HexChars);
    }
    /* END Method 2 */

    /* START Method 3 [1]  */
    public static String convertByteArrayToHexString3( byte[] ByteArray ) throws Exception {
        return new BigInteger(1, ByteArray).toString(16);
    }
    /* END Method 3 */

    /* START Method 4 [2] The simple way */
    public static String convertByteArrayToHexString4( byte[] ByteArray ) throws Exception {
        String HexString = "";
        for ( byte each_byte : ByteArray )
            HexString += Integer.toString( ( each_byte & 0xFF ) + 0x100, 16).substring( 1 );

        return HexString;
    }
    /* END Method 4 */

    /* START Method 5 [2] A faster way */
    static final byte[] HEX_CHAR_TABLE = {
            (byte)'0', (byte)'1', (byte)'2', (byte)'3',
            (byte)'4', (byte)'5', (byte)'6', (byte)'7',
            (byte)'8', (byte)'9', (byte)'a', (byte)'b',
            (byte)'c', (byte)'d', (byte)'e', (byte)'f'
    };

    public static String convertByteArrayToHexString5( byte[] ByteArray ) throws UnsupportedEncodingException {
        byte[] HexBytes = new byte[2 * ByteArray.length];
        int index = 0;

        for ( byte each_byte : ByteArray ) {
            HexBytes[index++] = HEX_CHAR_TABLE[ ((int)each_byte & 0xFF) >>> 4];
            HexBytes[index++] = HEX_CHAR_TABLE[ ((int)each_byte & 0xFF) & 0xF];
        }

        return new String(HexBytes, "ASCII");
    }
    /* END Method 5 */

    /* START Method 6 [2] A more elegant */
    static final String HEXES = "0123456789ABCDEF";
    public static String convertByteArrayToHexString6( byte[] ByteArray ) {
        if ( ByteArray == null ) return null;
        final StringBuilder HexString = new StringBuilder(2 * ByteArray.length);

        for ( final byte each_byte : ByteArray )
            HexString.append(HEXES.charAt((each_byte & 0xF0) >> 4)).append(HEXES.charAt((each_byte & 0x0F)));

        return HexString.toString();
    }
    /* END Method 6 */

    /* START Method 7 [1] */
    public static String convertByteArrayToHexString7( byte[] ByteArray ) {
        StringBuilder HexString = new StringBuilder();

        for (byte each_byte : ByteArray)
            HexString.append(String.format("%x", each_byte));

        return HexString.toString();
    }
    /* END Method 7 */

    /* START Method 8 [1] */
    private static final char[] BYTE2HEX=(
                    "000102030405060708090A0B0C0D0E0F"+
                    "101112131415161718191A1B1C1D1E1F"+
                    "202122232425262728292A2B2C2D2E2F"+
                    "303132333435363738393A3B3C3D3E3F"+
                    "404142434445464748494A4B4C4D4E4F"+
                    "505152535455565758595A5B5C5D5E5F"+
                    "606162636465666768696A6B6C6D6E6F"+
                    "707172737475767778797A7B7C7D7E7F"+
                    "808182838485868788898A8B8C8D8E8F"+
                    "909192939495969798999A9B9C9D9E9F"+
                    "A0A1A2A3A4A5A6A7A8A9AAABACADAEAF"+
                    "B0B1B2B3B4B5B6B7B8B9BABBBCBDBEBF"+
                    "C0C1C2C3C4C5C6C7C8C9CACBCCCDCECF"+
                    "D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF"+
                    "E0E1E2E3E4E5E6E7E8E9EAEBECEDEEEF"+
                    "F0F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF").toCharArray();

    public static String convertByteArrayToHexString8( byte[] ByteArray ) {
        final int ArrayLength = ByteArray.length;
        final char[] HexChars = new char[ArrayLength<<1];
        int HexIndex;
        int idx=0;
        int ofs=0;
        while ( ofs < ArrayLength ) {
            HexIndex = (ByteArray[ofs++] & 0xFF) << 1;
            HexChars[idx++] = BYTE2HEX[HexIndex++];
            HexChars[idx++] = BYTE2HEX[HexIndex];
        }
        return new String(HexChars);
    }
    /* END Method 8 */

    /* START Method 9 [1] */
    public static String convertByteArrayToHexString9( byte[] ByteArray ) {
        StringBuilder HexString = new StringBuilder();
        //for ( int i = 0; i < ByteArray.length; i++ )
        for ( byte each_byte : ByteArray )
            HexString.append(Integer.toString((each_byte & 0xFF) + 0x100, 16).substring(1));

        return HexString.toString();
    }
    /* END Method 9 */

    public static void main(String[] args) throws Exception {
        /*
        Test Example:
        ByteArray:  Hello, World!
        HexString:  48656C6C6F2C20576F726C6421
         */
        byte[] ByteArray = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
        System.out.print("inputBytes:\t");
        for ( byte each_byte : ByteArray )
            System.out.print( (char)each_byte );
        System.out.println();

        /* Call Method 1 */
        System.out.print("Method 1:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString1(ByteArray));

        /* Call Method 2 */
        System.out.print("Method 2:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString2(ByteArray));

        /* Call Method 3 */
        System.out.print("Method 3:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString3(ByteArray));

        /* Call Method 4 */
        System.out.print("Method 4:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString4(ByteArray));
        
        /* Call Method 5 */
        System.out.print("Method 5:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString5(ByteArray));

        /* Call Method 6 */
        System.out.print("Method 6:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString6(ByteArray));

        /* Call Method 7 */
        System.out.print("Method 7:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString7(ByteArray));

        /* Call Method 8 */
        System.out.print("Method 8:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString8(ByteArray));

        /* Call Method 9 */
        System.out.print("Method 9:\t");
        System.out.println(ConverterByteArrayToHexString.convertByteArrayToHexString9(ByteArray));

        /* Method 10 [1] */
        System.out.print("Method 10:\t");
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(ByteArray));

    }
}
