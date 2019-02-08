/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.composite.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joao Pereira
 * 
 */
public abstract class CompositeShape extends Shape {

	List<Shape> shapes;

	public CompositeShape() {
		this.shapes = createShapesList();
	}

	/**
	 * Remove a shape from this shape childrens
	 * 
	 * @param shape
	 *            the shape to remove
	 * @return true if the shape was present and was removed, false if the shape
	 *         was not present
	 */
	//TODO:
	public boolean removeShape(Shape shape) {
		return shapes.remove(shape);

	}

	/**
	 * Return the total shapes count in case this is a composite
	 * 
	 * @return the total count of shapes if the shape is composite. -1 otherwise
	 */
	//TODO:
	public int getShapeCount() {
		if( this == this.asComposite() ) {
			return shapes.size();
		} else {
			return -1;
		}
	}

	/**
	 * Add a shape to this shape.
	 * 
	 * @param shape
	 *            The shape to add
	 * @throws ShapeDoesNotSupportChildren
	 *             if this shape is not a composite
	 */
	//TODO:
	public void addShape(Shape shape) throws ShapeDoesNotSupportChildren {
		if( this == this.asComposite() ) {
			shapes.add(shape);
		} else {
			throw new ShapeDoesNotSupportChildren();
		}		
	}
	//TODO:
	public List<Shape> getShapes() {

		return shapes;

	}

	/**
	 * @param circle
	 * @return
	 */
	//TODO:
	public List<Shape> getShapesByType(ShapeType circle) {
		
		List<Shape> list = createShapesList();
		for( Shape s : shapes ) {
			if(s.getType() == circle) {
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * Return all shapes that are leafs in the tree
	 * 
	 * @return
	 */
	//TODO:
	public List<Shape> getLeafShapes() {
		List<Shape> list = createShapesList();
		for( Shape s : shapes ) {
			if(s.asComposite() == s) {
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * Factory method for the list of children of this shape
	 * 
	 * @return
	 */
	//TODO:
	protected List<Shape> createShapesList() {
		return new ArrayList <Shape> ();
	
	}
}
