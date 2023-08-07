package org.jvnet.basicjaxb_annox.parser.java.visitor;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb_annox.japa.parser.ast.visitor.AbstractGenericExpressionVisitor;
import org.jvnet.basicjaxb_annox.parser.exception.ValueParseException;

import japa.parser.ast.Node;

public class ExpressionVisitor<V> extends
		AbstractGenericExpressionVisitor<V, Void> {
	protected final Class<?> targetClass;

	public ExpressionVisitor(Class<?> targetClass) {
		requireNonNull(targetClass);
		this.targetClass = targetClass;
	}

	@Override
	public V visitDefault(Node n, Void arg) {
		throw new RuntimeException(new ValueParseException(n,
				this.targetClass));
	}

}