/**
 * Copyright 2011-2013 Akiban Technologies, Inc.
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
 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * DECLARE a CURSOR on some statement.
 */

public class DeclareStatementNode extends StatementNode
{
    private String name;
    private StatementNode statement;

    /**
     * Initializer for an DeclareStatementNode
     *
     * @param name The name of the statement
     * @param statement The statement to be executed
     */

    public void init(Object name,
                     Object statement) {
        this.name = (String)name;
        this.statement = (StatementNode)statement;
    }
    
    public String getName() {
        return name;
    }

    public StatementNode getStatement() {
        return statement;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);
        
        DeclareStatementNode other = (DeclareStatementNode)node;
        this.statement = (StatementNode)getNodeFactory().copyNode(other.statement,
                                                                  getParserContext());
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "name: " + name + "\n" +
            super.toString();
    }

    public String statementToString() {
        return "DECLARE";
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        printLabel(depth, "statement: ");
        statement.treePrint(depth + 1);
    }

    /**
     * Accept the visitor for all visitable children of this node.
     * 
     * @param v the visitor
     *
     * @exception StandardException on error
     */
    void acceptChildren(Visitor v) throws StandardException {
        super.acceptChildren(v);

        statement = (StatementNode)statement.accept(v);
    }

}
