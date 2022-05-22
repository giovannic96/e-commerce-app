import React from "react";
import { Link } from "react-router-dom";

const NavigationBar = (): JSX.Element => {
	return <div>
		<div>
			<Link to="/" aria-label="Home">
				<button>
					Home
				</button>
			</Link>
			<Link to="/admin" aria-label="Admin">
				<button>
					Admin
				</button>
			</Link>
		</div>
	</div>
};

export default NavigationBar;
