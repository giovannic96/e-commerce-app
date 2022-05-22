import React from 'react';
import { fireEvent, render, screen } from '@testing-library/react'
import App from "./App";
import {homePageHeaderClass} from "./pages/Home/Home.test";

describe('App', () => {
	it('should render the Home page by default', () => {
		const {container} = render(<App/>)
		expect(container.getElementsByClassName(homePageHeaderClass).length).toBe(1)
	})

	it('should render the Admin page when the Admin link is clicked', async () => {
		const {container} = render(<App/>)

		const linkToAdmin = screen.getByRole('link', {name: "Admin"})

		fireEvent.click(linkToAdmin)

		expect(container.getElementsByClassName(homePageHeaderClass).length).toBe(0)
	})

	it('should return to the Home page when the Home link is clicked', async () => {
		const {container} = render(<App/>)

		const linkToAdmin = screen.getByRole('link', {name: "Admin"})
		fireEvent.click(linkToAdmin)

		const linkToHome = screen.getByRole('link', {name: "Home"})
		fireEvent.click(linkToHome)

		expect(await screen.queryByRole("progressbar", {hidden: true})).toBeInTheDocument()
		expect(container.getElementsByClassName(homePageHeaderClass).length).toBe(1)
	})
})
