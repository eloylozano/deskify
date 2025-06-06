<script>
	import Nav from '../../components/Nav.svelte';
	import FaqList from '../../components/FaqList.svelte';
	import ProtectedRoute from '../../components/ProtectedRoute.svelte';

	let faqs = [
		{
			question: 'How do I register?',
			answer:
				"You can register by clicking on the 'Register' button located at the top right corner of the homepage. You will be redirected to a registration form where you'll need to enter your name, email address, and a secure password. Once you've filled in your details, click 'Submit' and check your email for a confirmation link to activate your account.",
			image: '/faq/register.png'
		},
		{
			question: "What is the platform's main feature?",
			answer:
				'Our platform provides real-time communication and task collaboration for teams of all sizes. With features like instant messaging, file sharing, video conferencing, and integrated calendars, users can manage their projects more efficiently and stay connected regardless of their location.'
		},
		{
			question: 'How can I update a ticket ?',
			answer:
				'If you click inside a ticket, you will see a panel on the right, which you can collapse or expand with the [>] button. Selectors will then appear, allowing you to update the ticket`s status, priority, category, and assigned agent. Finally, click the update button, and the ticke`s new information will appear in the lower right corner.',
			image: '/faq/update.png'
		},
		{
			question: 'How do I change my password?',
			answer:
				"To change your password, log into your account and go to the 'Settings' page by clicking on your profile icon. You'll find the option to update your password. Enter your current password, then type your new one and confirm it. Don't forget to click 'Save Changes' to apply the update.",
			image: '/faq/password.png'
		},
		{
			question: 'Is there a mobile app available?',
			answer:
				'Are you impatient? We are too. We´re working around the clock to bring our native app to mobile devices. It will be available for both Android and iOS. Stay tuned for the latest news!',
			image: '/faq/mobile-app.png'
		},
		{
			question: 'How do I upgrade my plan?',
			answer:
				"To upgrade your plan, go to 'Billing' under your account profile. You will see the current plan you're subscribed to and available upgrade options. Choose the plan that best fits your needs and follow the instructions to complete the payment securely. Your account will be updated instantly.",
			image: '/faq/upgrade.png'
		},
		{
			question: 'What should I do if I encounter a technical issue?',
			answer:
				"If you're having a technical issue, you're in luck! You're using a dedicated app, who would have thought? First, try refreshing the page or restarting the app. If the problem persists, create a new issue using the [+] icon. You can submit a ticket detailing your problem, and our team will get back to you within 24 hours.",
			image: '/faq/support.png'
		},
		{
			question: 'Is my data safe and secure on this platform?',
			answer:
				'Yes, your data security is our top priority. We use end-to-end encryption, secure cloud storage, and follow industry best practices to ensure all your information remains confidential. Additionally, regular backups and system audits are performed to maintain a high level of protection.'
		}
	];
</script>

<ProtectedRoute>
	<div class="flex h-screen bg-emerald-200">
		<Nav />

		<div class="flex-1 overflow-y-auto">
			<FaqList {faqs} />
		</div>
	</div>
</ProtectedRoute>
