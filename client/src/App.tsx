import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import './App.css';
import Home from "./pages/Home/Home";
import Admin from "./pages/Admin/Admin";
import NavigationBar from "./component/NavigationBar/NavigationBar";

const App = (): JSX.Element => {

	return <div>
		<Router>
			<NavigationBar />
			<div>
				<Routes>
					<Route path={"/"} element={<Home />} />
					<Route path={"/admin"} element={<Admin />} />
				</Routes>
			</div>
		</Router>
	</div>
}

export default App;
