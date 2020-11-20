import React, {Component} from 'react';
import Table from 'react-bootstrap/Table'

import Alert from 'react-bootstrap/Alert'

class DataTable extends Component {
    render() {
        return (
            <Table striped bordered hover variant="dark">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Product Description</th>
                    <th>Product Category</th>
                    <th>Product Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td colSpan="2">Larry the Bird</td>
                    <td>@twitter</td>
                </tr>
                </tbody>
            </Table>
        );
    }
}

export default DataTable;