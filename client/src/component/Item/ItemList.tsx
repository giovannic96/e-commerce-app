import Loader from "../Loading/Loader";
import {useEffect, useState} from "react";
import {API_BASE_URL} from "../../constants";

const ItemList = (): JSX.Element => {
	const [isLoading, setIsLoading] = useState(true);
	const [items, setItems] = useState([]);

	useEffect(() => {
		fetch(`${API_BASE_URL}/items`)
			.then(response => response.json())
			.then(data => {
				console.log(data)
				setItems(data)
				setIsLoading(false)
			})
			.catch(err => {console.log(err)})
	}, []);

	const itemList = <div>
		{items.length}
	</div>

	return <div>
		{isLoading ? <Loader /> : itemList}
	</div>
}

export default ItemList;