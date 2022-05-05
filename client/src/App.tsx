import React, {useEffect} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

	const baseURL = process.env.NODE_ENV === 'production' ? "api/v1/items" : "http://localhost:8081/api/v1/items"

	useEffect(() => {
		fetch(baseURL)
			.then(response => response.json())
			.then(data => {
				console.log(data)
			})
			.catch(err => {console.log(err)})
	}, []);

	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo" />
				<p>
					Edit <code>src/App.tsx</code> and save to reload.
				</p>
				<a
					className="App-link"
					href="https://reactjs.org"
					target="_blank"
					rel="noopener noreferrer"
				>
					Learn React
				</a>
			</header>
		</div>
	);
}

export default App;
