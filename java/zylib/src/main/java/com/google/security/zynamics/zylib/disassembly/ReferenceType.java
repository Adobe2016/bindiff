// Copyright 2011 Google Inc. All Rights Reserved.

package com.google.security.zynamics.zylib.disassembly;

public enum ReferenceType {
  CONDITIONAL_TRUE, CONDITIONAL_FALSE, UNCONDITIONAL, SWITCH, CALL_DIRECT, CALL_INDIRECT, CALL_VIRTUAL, DATA, DATA_STRING;

  /**
   * Converts an integer to the corresponding reference type.
   * 
   * @param type integer indicating the type.
   * 
   * @return the reference type corresponding to the integer argument.
   */
  public static ReferenceType convertIntToReferenceType(final int type) {
    switch (type) {
      case 0:
        return ReferenceType.CONDITIONAL_TRUE;
      case 1:
        return ReferenceType.CONDITIONAL_FALSE;
      case 2:
        return ReferenceType.UNCONDITIONAL;
      case 3:
        return ReferenceType.SWITCH;
      case 4:
        return ReferenceType.CALL_DIRECT;
      case 5:
        return ReferenceType.CALL_INDIRECT;
      case 6:
        return ReferenceType.CALL_INDIRECT;
      case 7:
        return ReferenceType.CALL_VIRTUAL;
      case 8:
        return ReferenceType.DATA;
      case 9:
        return ReferenceType.DATA_STRING;
      default:
        throw new IllegalArgumentException("Error type is outside of reference type range");
    }
  }

  /**
   * Turns a reference type into a numerical value that can be stored in the database.
   * 
   * @param referenceType The reference type.
   * 
   * @return The numerical value of the reference type.
   */
  public static int convertReferenceTypeToInt(final ReferenceType referenceType) {
    return referenceType.ordinal();
  }

  /**
   * Indicates whether a reference is a code reference.
   * 
   * @param referenceType The reference type.
   * 
   * @return true if the reference is a code reference false otherwise.
   */
  public static boolean isCodeReference(final ReferenceType referenceType) {
    return !isDataReference(referenceType);
  }

  /**
   * Indicates whether a reference is a data reference.
   * 
   * @param referenceType The reference type.
   * 
   * @return true if the reference is a data type reference false otherwise.
   */
  public static boolean isDataReference(final ReferenceType referenceType) {
    if (referenceType.compareTo(CALL_VIRTUAL) < 0) {
      return false;
    } else {
      return true;
    }
  }
}