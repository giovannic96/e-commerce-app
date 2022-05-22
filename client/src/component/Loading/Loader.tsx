import "./Loader.css";
import React from "react";

const Loader = (): JSX.Element => {
	return (
		<div className="spinner-container">
			<div role="progressbar" className="loading-spinner"/>
		</div>
	);
}

export default Loader;