import React from "react";
import {render, screen} from "@testing-library/react";
import { BrowserRouter as Router } from "react-router-dom";
import NavigationBar from "./NavigationBar";

describe("Navigation bar", () => {
	beforeEach(() => {
		render(
			<Router>
				<NavigationBar />
			</Router>
		)
	})

	it('should render exactly 2 links', () => {
		const links = screen.queryAllByRole('link', {})
		expect(links.length).toEqual(2)
	})

	it('should render Home link', () => {
		expect(screen.queryByRole('link', {name: "Home"})).toBeInTheDocument()
	})

	it('should render Admin link', () => {
		expect(screen.queryByRole('link', {name: "Admin"})).toBeInTheDocument()
	})
});
