/*
 * Copyright (c) 2021. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package dk.msdo.caveservice.domain;


import lombok.SneakyThrows;

/**
 * This class represents a position in the 3D space of the cave (x,y,z) as a
 * normal Cartesian coordinate system. It is mostly used for conversion to and
 * from the position string which is the 'primary key' in the storage layer.
 * 
 * @author Henrik Baerbak Christensen, Aarhus University.
 * 
 */
public class Point3 {

  private int x;
  private int y;
  private int z;

  public Point3(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void translate(Direction direction) {
    x += offset[0][direction.ordinal()];
    y += offset[1][direction.ordinal()];
    z += offset[2][direction.ordinal()];
  }
  
  public int x() { return x; }
  public int y() { return y; }
  public int z() { return z; }
  
  @SneakyThrows
  public Object clone() {
    super.clone();
    return new Point3(x,y,z);
  }

  private int[][] offset = {
      { 0, 0, +1, -1, 0, 0 },
      { 1, -1, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 1, -1 }
  };
  
  /**
   * return the position string which encodes a Point3 as a string useful as
   * primary key into a database table/ collection of rooms.
   * 
   * @return (x,y,z) as string
   */
  public String getPositionString() {
    return "("+x+","+y+","+z+")";
  }
  
  public String toString() {
    return getPositionString();
  }

  /**
   * Given a position string, create a position object.
   * 
   * @param positionString
   *          the string representing a position
   * @return a Point3 of the same position
   */
  public static Point3 parseString(String positionString) {
    // Yes, really inefficient, but what the heck... -HBC
    String changed = positionString.replace("(", "").replace(")","").replace(","," ");
    String[] tokens = changed.split("\\s");
    assert tokens.length == 3;
    int x = Integer.parseInt(tokens[0]);
    int y = Integer.parseInt(tokens[1]);
    int z = Integer.parseInt(tokens[2]);
    return new Point3(x,y,z);
  }

}
